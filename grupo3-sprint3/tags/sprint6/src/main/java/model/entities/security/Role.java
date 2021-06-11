package model.entities.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that provide the name and the salary of a object. Objects of this class will be used as
 * attribute in Collaborator Class.
 * 
 * @Version: 1.5.2
 * 
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 * @author Pablo Mafessoli <mafessolip@gmail.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
@Entity
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(unique = true)
  private String name;

  private AccessLevel accessLevel;

  /*
   * @ManyToOne
   * 
   * @JoinColumn(name = "collaborator_role") private Collaborator collab;
   */

  public Role() {}

  public Role(String name) {
    this.name = name;
  }

  public Role(String name, AccessLevel accessLevel) {
    this.name = name;
    setAccessLevel(accessLevel);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AccessLevel getAccessLevel() {
    return accessLevel;
  }

  public void setAccessLevel(AccessLevel accessLevel) {
    try {
      this.accessLevel = accessLevel;
    } catch (ExceptionInInitializerError er) {
      throw new ExceptionInInitializerError("erro no access level");
    }

  }

}
