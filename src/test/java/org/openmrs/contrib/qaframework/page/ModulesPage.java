package org.openmrs.contrib.qaframework.page;

import org.openmrs.uitestframework.page.Page;

public class ModulesPage extends Page {

	private static final String ADMIN_MODULE_LIST_PATH = "/admin/modules/module.list";

	public ModulesPage(Page page) {
		super(page);
	}

	@Override
	public String getPageUrl() {
		return ADMIN_MODULE_LIST_PATH;
	}
}
