package model.dao;

import java.util.ArrayList;

import model.dao.hibernate.ServiceProviderHibernate;
import model.entities.person.ServiceProvider;


public class ServiceProviderDAO {

  private ServiceProviderHibernate serviceProviderHibernate;

  public ServiceProviderDAO() {
    this.serviceProviderHibernate = new ServiceProviderHibernate();
  }

  public int insert(ServiceProvider serviceProviderModel) {
    return serviceProviderHibernate.insert(serviceProviderModel);
  }

  public int update(ServiceProvider serviceProvider) {
    return serviceProviderHibernate.update(serviceProvider);
  }

  public int delete(int id) {
    return serviceProviderHibernate.delete(id);
  }

  public ServiceProvider findByCnpj(String cnpj) {
    return (ServiceProvider) serviceProviderHibernate.find("cnpj", cnpj);
  }

  public ServiceProvider findById(int id) {
    return (ServiceProvider) serviceProviderHibernate.find("id", String.valueOf(id));
  }

  public ArrayList<ServiceProvider> getAll() {
    return serviceProviderHibernate.getAll();
  }

}
