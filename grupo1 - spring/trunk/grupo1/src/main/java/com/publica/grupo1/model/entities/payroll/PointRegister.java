package com.publica.grupo1.model.entities.payroll;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import com.publica.grupo1.model.dao.point.PointDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.point.Point;
import com.publica.grupo1.model.entities.point.PointType;
import com.publica.grupo1.util.datetime.DateUtil;

/**
 * Class that stores points of a collaborator, @see {@link Collaborator} and
 * {@link Point}.
 * 
 * @author Diego Leon
 * @author Augusto Kalahary
 * @author Pablo
 *
 */
public class PointRegister {

	private Collaborator collaborator;
	private PointDAO dao;
	private double weekTime, weekExtraTime, sundayExtraTime, nocturnalWeekAdditional;

	/**
	 * HashMap with day as key and as value a arrayList with the points of the day
	 * key.
	 */
	private HashMap<LocalDate, ArrayList<Point>> points;

	/**
	 * @TODO: use this for store not closed points in calculateDayPoints method when
	 *        check if dayPoints are even.
	 */
	// private ArrayList<Point> monthNotClosedPoints = new ArrayList<>();

	/**
	 * final variable that stores the max possible nocturnal workload.
	 */
	public final double NOCTURNAL_MAX_WORK_HOURS = 7.0;

	/**
	 * final variables for store when nocturnal period starts and when it ends;
	 */
	public final double NOCTURNAL_START_WORK_HOURS = 22.0;
	public final double NOCTURNAL_END_WORK_HOURS = 5.0;

	/**
	 * final variables for store max quantity of nocturnal hours in the end of a day
	 * (22 until 24) and at start (0 until 5)
	 */
	public final double NOCTURNAL_FIRST_HALF_WORK_HOURS = 2.0;
	public final double NOCTURNAL_SECOND_HALF_WORK_HOURS = 5.0;

	/**
	 * Static PointType.
	 */

	public PointRegister(Collaborator collab, PointDAO dao) {
		this.dao = dao;
		this.collaborator = collab;
	}

	/**
	 * calculate all the points of the atribute collaborator in the param date. This
	 * method will calculate the points of the date day until the last day of month.
	 * 
	 * @param date : date of the points
	 * 
	 * @author Diego Leon
	 * @author Pablo
	 */
	public void calculateDatePoints(LocalDate date) {
		points = dao.getCollaboratorPointsByDate(collaborator, date);
		boolean isLeapYear = date.isLeapYear();
		int monthSize = date.getMonth().length(isLeapYear);

		for (int i = 0; i + date.getDayOfMonth() <= monthSize; i++)
			calculateDayPoints(points.get(date.plusDays(i)));
	}

	/**
	 * calculate the worked hours, checking extra hours and ignoring not closed
	 * points, but checking if last and first day points are conected with the last
	 * or next day point.
	 * 
	 * 
	 * @param dayPoints : points of the day. If the size is odd than the day have a
	 *                  not closed point.
	 * 
	 * @author Diego Leon
	 * @author Pablo
	 * @author Augusto Kalahary
	 * 
	 *         TODO: specified collaborator work horary, because when calculate
	 *         noctural hours the remnant hours are set as extra hours
	 */
	public void calculateDayPoints(ArrayList<Point> dayPoints) {
		if (dayPoints == null || dayPoints.isEmpty())
			return;

		verifyIfHaveNocturnalNotClosedPoints(dayPoints.get(dayPoints.size() - 1));

		double normalWorkedHours = 0;
		double extraHours = 0;
		double workedNocturnalHours = 0;
		/**
		 * in case of even day points size this will ignore the last point.
		 */
		int numberOfClosedPoints = dayPoints.size() % 2 == 0 ? dayPoints.size() - 1 : dayPoints.size() - 2;

		boolean isSunday = dayPoints.get(0).getDate().getDayOfWeek().equals(DayOfWeek.SUNDAY);
		boolean haveExtraHoursOfWork;

		for (int i = 0; i < numberOfClosedPoints; i += 2) {

			haveExtraHoursOfWork = normalWorkedHours > collaborator.getPermission().getRole().getWorkLoad();

			if (haveExtraHoursOfWork || isSunday) {
				extraHours += DateUtil.getDifferenceInHoursWithMinutesBetweenDates(dayPoints.get(i).getDate(),
						dayPoints.get(i + 1).getDate());
			} else {
				if (verifyIfTheHourIsInNocturnalPeriod(dayPoints.get(i).getDate().getHour())
						|| verifyIfTheHourIsInNocturnalPeriod(dayPoints.get(i + 1).getDate().getHour())) {

					double workedHours = 0;

					LocalDateTime earlierDate = dayPoints.get(i).getDate();
					LocalDateTime laterDate = dayPoints.get(i + 1).getDate();

					/**
					 * when the hour is equal to 23 the method will calculate the difference without
					 * the hour 24:00, because of this workedHours receive an increment.
					 */
					if (laterDate.getHour() == 23)
						workedHours++;

					workedHours += DateUtil.getDifferenceInHoursWithMinutesBetweenDates(earlierDate, laterDate);

					boolean isBetweenTenAndMidNight = laterDate.getHour() >= NOCTURNAL_START_WORK_HOURS
							&& laterDate.getHour() < 24;

					boolean isBetweenMidNightAndFiveHours = earlierDate.getHour() >= 0
							&& earlierDate.getHour() <= NOCTURNAL_END_WORK_HOURS;

					if (isBetweenTenAndMidNight) {

						haveExtraHoursOfWork = workedHours > NOCTURNAL_FIRST_HALF_WORK_HOURS;

						if (haveExtraHoursOfWork) {
							workedNocturnalHours = NOCTURNAL_FIRST_HALF_WORK_HOURS;
							extraHours += workedHours - workedNocturnalHours;
						} else {
							workedNocturnalHours += workedHours;
						}

					} else if (isBetweenMidNightAndFiveHours) {

						haveExtraHoursOfWork = workedHours > NOCTURNAL_SECOND_HALF_WORK_HOURS;

						if (haveExtraHoursOfWork) {
							workedNocturnalHours = NOCTURNAL_SECOND_HALF_WORK_HOURS;
							extraHours += workedHours - workedNocturnalHours;
						} else {
							workedNocturnalHours += workedHours;
						}
					}
				} else {
					double workedHours = DateUtil.getDifferenceInHoursWithMinutesBetweenDates(
							dayPoints.get(i).getDate(), dayPoints.get(i + 1).getDate()) + normalWorkedHours;

					haveExtraHoursOfWork = workedHours > collaborator.getPermission().getRole().getWorkLoad();

					if (haveExtraHoursOfWork) {
						normalWorkedHours = collaborator.getPermission().getRole().getWorkLoad();
						extraHours += workedHours - normalWorkedHours;
					} else {
						normalWorkedHours = workedHours;
					}
				}
			}
		}
		nocturnalWeekAdditional += workedNocturnalHours;
		weekTime += normalWorkedHours;
		calculateExtraHours(extraHours, isSunday);
	}

	/**
	 * 
	 * Verify if the last day point is a entry, @see enum in Point, if it is than
	 * check if the next day have a exit as the first point, than add in the day of
	 * lastPoint param a new Point with last LocalDate possible hour, and in the
	 * next day a Point with the first possible LocalDate hour.
	 * 
	 * @param lastDayPoint last point of the day.
	 * 
	 * @author Diego Leon
	 * @author Augusto Kalahary
	 */
	private void verifyIfHaveNocturnalNotClosedPoints(Point lastDayPoint) {
		LocalDate actualDayDate = lastDayPoint.getDate().toLocalDate();
		LocalDate nextDayDate = actualDayDate.plusDays(1);
		
		if (lastDayPoint.getPointType().equals(PointType.ENTRY) && points.get(nextDayDate) != null
				&& points.get(nextDayDate).get(0).getPointType().equals(PointType.EXIT)) {

			LocalDateTime actualDate = lastDayPoint.getDate();
			LocalDateTime nextDayDateTime = LocalDateTime.of(actualDate.toLocalDate().plusDays(1), LocalTime.of(0, 0));

			points.get(actualDayDate).add(new Point(collaborator,
					LocalDateTime.of(actualDate.getYear(), actualDate.getMonth(), actualDate.getDayOfMonth(), 23, 0),
					PointType.EXIT));

			points.get(nextDayDate).add(0, new Point(collaborator, nextDayDateTime, PointType.ENTRY));
		}
	}

	/***
	 * Verify if the point was register between 22h and 5h
	 * 
	 * @param hour
	 * 
	 * @return true if the hour is in the specified range.
	 * 
	 * @author Augusto Kalahary
	 */
	private boolean verifyIfTheHourIsInNocturnalPeriod(int hour) {
		return (hour >= NOCTURNAL_START_WORK_HOURS || hour <= NOCTURNAL_END_WORK_HOURS);
	}

	/**
	 * calculate extra hours and if they are week extra, nocturnal week extra or
	 * sunday extra.
	 * 
	 * @param extraHours : the extra hours
	 * @param isSunday   : if is a sunday
	 * 
	 * @author Diego Leon
	 * @author Pablo
	 */
	private void calculateExtraHours(double extraHours, boolean isSunday) {
		if (isSunday) {
			sundayExtraTime += extraHours;
		} else {
			weekExtraTime += extraHours;
		}
	}

	public Collaborator getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
	}

	public PointDAO getDao() {
		return dao;
	}

	public void setDao(PointDAO dao) {
		this.dao = dao;
	}

	public double getWeekTime() {
		return weekTime;
	}

	public void setWeekTime(double weekTime) {
		this.weekTime = weekTime;
	}

	public double getWeekExtraTime() {
		return weekExtraTime;
	}

	public void setWeekExtraTime(double weekExtraTime) {
		this.weekExtraTime = weekExtraTime;
	}

	public double getSundayExtraTime() {
		return sundayExtraTime;
	}

	public void setSundayExtraTime(double sundayExtraTime) {
		this.sundayExtraTime = sundayExtraTime;
	}

	public double getNocturnalWeekAdditional() {
		return nocturnalWeekAdditional;
	}

	public void setNocturnalWeekAdditional(double nocturnalWeekAdditional) {
		this.nocturnalWeekAdditional = nocturnalWeekAdditional;
	}

	public HashMap<LocalDate, ArrayList<Point>> getMonthPoints() {
		return points;
	}

	public void setMonthPoints(HashMap<LocalDate, ArrayList<Point>> monthPoints) {
		this.points = monthPoints;
	}
}
