package com.webTechProject.webtech_app.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Book.class)
public abstract class Book_ {

	public static volatile SingularAttribute<Book, String> ISBN;
	public static volatile SingularAttribute<Book, String> author;
	public static volatile SingularAttribute<Book, String> yearPublished;
	public static volatile SingularAttribute<Book, String> Publisher;
	public static volatile SingularAttribute<Book, String> bookName;
	public static volatile SingularAttribute<Book, String> bookCategory;
	public static volatile SingularAttribute<Book, Integer> bookId;

}

