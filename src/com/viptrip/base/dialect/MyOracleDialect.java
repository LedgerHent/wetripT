package com.viptrip.base.dialect;

import java.sql.Types;

import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.type.StringType;

public class MyOracleDialect extends Oracle10gDialect{
	
	public MyOracleDialect() {
		super();
		registerHibernateType(Types.NVARCHAR, StringType.INSTANCE.getName());
	}
	
}
