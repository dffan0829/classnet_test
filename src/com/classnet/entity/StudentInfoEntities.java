
package com.classnet.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "student", catalog = "classnet")
public class StudentInfoEntities  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rowguid;
	private Integer stuNo;
	private String stuName;
	private String gender;
	private String stuTel;
	private String email;
	private String address;
	private Integer classno;

	@Id
	//配置uuid，本来jpa是不支持uuid的，但借用hibernate的方法可以实现。
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getRowguid() {
		return rowguid;
	}

	public void setRowguid(String rowguid) {
		this.rowguid = rowguid;
	}

	@Column(name = "stuno", length = 50, nullable = false)
	public Integer getStuNo() {
		return stuNo;
	}

	public void setStuNo(Integer stuNo) {
		this.stuNo = stuNo;
	}

	@Column(name = "stuname", length = 50, nullable = false)
	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	@Column(name = "gender", length = 2, nullable = false)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "stutel", length = 50, nullable = false)
	public String getStuTel() {
		return stuTel;
	}

	public void setStuTel(String stuTel) {
		this.stuTel = stuTel;
	}

	@Column(name = "email", length = 50, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", length = 50, nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "classno", length = 50, nullable = false)
	public Integer getClassno() {
		return classno;
	}

	public void setClassno(Integer classno) {
		this.classno = classno;
	}

}
