package edu.mayo.cts2.framework.plugin.service.ecis.mybatis.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.StringTypeHandler;

public class UuidUriTypeHandler extends BaseTypeHandler<String> {
	
	private static final String UUID_URI_PREFIX = "urn:uuid:";

	private StringTypeHandler delegate;
	
	public UuidUriTypeHandler(){
		super();
		delegate = new StringTypeHandler();
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			String parameter, JdbcType jdbcType) throws SQLException {
		parameter = this.trimPrefix(parameter);
		
		delegate.setNonNullParameter(ps, i, parameter, jdbcType);
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		return this.addPrefix(
				delegate.getNullableResult(rs, columnName));
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return this.addPrefix(
			delegate.getNullableResult(rs, columnIndex));
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return this.addPrefix(
			delegate.getNullableResult(cs, columnIndex));
	}
	
	protected String trimPrefix(String uuid){
		return StringUtils.removeStart(UUID_URI_PREFIX, uuid);
	}
	
	protected String addPrefix(String uuid){
		return UUID_URI_PREFIX + uuid;
	}

}
