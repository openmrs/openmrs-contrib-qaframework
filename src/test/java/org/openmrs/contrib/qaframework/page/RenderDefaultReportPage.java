package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;

public class RenderDefaultReportPage extends Page {

	public RenderDefaultReportPage(Page page) {
		super(page);
	}

	@Override
	public String getPageUrl() {
		return "/reporting/reports/renderDefaultReport";
	}
}
