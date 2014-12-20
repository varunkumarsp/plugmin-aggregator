package org.openxava.annotations.extended.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.Aggregates;
import org.openxava.annotations.extended.DataSource;
import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.JsonKeyValuesProviderI;
import org.openxava.annotations.extended.JsonValueProvider;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(DataSource.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class DataSourceVo implements JsonKeyValuesProviderI {

	@DefaultValue("false")
	private Boolean autoSync;
	
	@DefaultValue("false")
	private Boolean batch;
	
	private DataSourceSchemaVo schema;

	private DataSourceTransportVo transport;
	
	private List<Aggregate> aggregate;
	
	private Integer pageSize = 10;
	
	private Boolean serverGrouping = true;
	private Boolean serverPaging = true;
	private Boolean serverFiltering = true;
	private Boolean serverSorting = true;
	private Boolean serverAggregates = true;

	@JsonValueProvider
	private Filter filter;
	
	@JsonIgnore
	private List<ColumnVo> columns;

	
	private DataSourceVo() {
	}
	
	public DataSourceVo(List<ColumnVo> columns) {
		this.columns = columns;
		initSchema();
	}
	
	
	public Boolean isAutoSync() {
		return autoSync;
	}

	public void setAutoSync(Boolean autoSync) {
		if(autoSync != null)
			this.autoSync = autoSync;
	}

	public Boolean isBatch() {
		return batch;
	}

	public void setBatch(Boolean batch) {
		if(batch != null)
			this.batch = batch;
	}

	public DataSourceSchemaVo getSchema_() {
		if(schema == null)
			try {
				schema = DataSourceSchemaVo.instance(columns);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return schema;
	}
	
	public DataSourceSchemaVo getSchema() {
		return schema;
	}

	public void setSchema(DataSourceSchemaVo schema) {
		if(schema != null)
			this.schema = schema;
	}

	public DataSourceTransportVo getTransport_(String createUrl, String readUrl, String updateUrl, String destroyUrl) {
		if(transport == null)
			try {
				transport = DataSourceTransportVo.instance(createUrl, readUrl, updateUrl, destroyUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return transport;
	}
	
	public DataSourceTransportVo getTransport() {
		return transport;
	}

	public void setTransport(DataSourceTransportVo transport) {
		if(transport != null)
			this.transport = transport;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize != null)
			this.pageSize = pageSize;
	}

	public List<Aggregate> getAggregate() {
		return aggregate;
	}

	public void addAggregates(List<Aggregate> aggregates) {
		if(aggregates != null) {
			if(this.aggregate == null)
				this.aggregate = new ArrayList<Aggregate>();
			this.aggregate.addAll(aggregates);
		}
	}
	
	public void addAggregate(Aggregate aggregate) {
		if(aggregate != null) {
			if(this.aggregate == null)
				this.aggregate = new ArrayList<Aggregate>();
			this.aggregate.add(aggregate);
		}
	}
	
	public Boolean getServerGrouping() {
		return serverGrouping;
	}

	public void setServerGrouping(Boolean serverGrouping) {
		if(serverGrouping != null)
			this.serverGrouping = serverGrouping;
	}
	
	public Boolean isServerPaging() {
		return serverPaging;
	}

	public void setServerPaging(Boolean serverPaging) {
		if(serverPaging != null)
			this.serverPaging = serverPaging;
	}

	public Boolean isServerFiltering() {
		return serverFiltering;
	}

	public void setServerFiltering(Boolean serverFiltering) {
		if(serverFiltering != null)
			this.serverFiltering = serverFiltering;
	}

	public Boolean isServerSorting() {
		return serverSorting;
	}

	public void setServerSorting(Boolean serverSorting) {
		if(serverSorting != null)
			this.serverSorting = serverSorting;
	}

	public Boolean isServerAggregates() {
		return serverAggregates;
	}

	public void setServerAggregates(Boolean serverAggregates) {
		if(serverAggregates != null)
			this.serverAggregates = serverAggregates;
	}
	
	public DataSourceVo copyFrom(DataSource dataSource) {
		setAutoSync(dataSource.autoSync().getBool());
		setBatch(dataSource.batch().getBool());
//		setPageSize(dataSource.pageSize() > 0 ? dataSource.pageSize() : null);
		addAggregates(aggregates());
		return this;
	}

	private List<Aggregate> aggregates() {
		List<Aggregate> aggregates = new ArrayList<Aggregate>();
		for (ColumnVo column : columns) {
			List<Aggregates> agg = column.getAggregates();
			if(CollectionUtils.isNotEmpty(agg)) {
				String field = column.getField();
				for (Aggregates agg2 : agg) {
					aggregates.add(new Aggregate(field, agg2));
				}
			}
		}
		return aggregates;
	}


	private void initSchema() {
		setSchema(getSchema_());
	}

	public Filter getFilter_() {
		if(filter == null)
			try {
				filter = Filter.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return filter;
	}
	
	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		if(filter != null)
			this.filter = filter;
	}
	
	public void addFilter(FilterItem filter) throws InstantiationRestrictedException {
		getFilter_().addFilter(filter);
	}

	@Override
	public Map<String, Object> getKeyValues(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getRawKeyValues(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(String fieldName) {
		if(fieldName.equals("filter")) {
			return resolveFilter();
		}
		return null;
	}

	private Object resolveFilter() {
		if(filter != null) {
			if(StringUtils.isEmpty(filter.getLogic()))
				return filter.getFilters();
			else
				return filter;
		}
		return null;
	}
}
