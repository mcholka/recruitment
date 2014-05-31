package com.recruitment.common;

import javax.enterprise.inject.Model;

/**
 * Created by mcholka on 2014-05-31. Enjoy!
 */
@Model
public class ViewConfig {
    public static final int ROW_PER_PAGE_DEFAULT = 250;
    public static final int ROW_PER_PAGE_SECOND = 500;
    public static final int ROW_PER_PAGE_THIRD = 1000;
    public static final String PAGINATOR_TEMPLATE = "{RowsPerPageDropdown} {FirstPageLink} {PreviousPageLink} {PageLinks} {NextPageLink} {LastPageLink}";
    public static final String ROWS_PER_PAGE_TEMPLATE = ROW_PER_PAGE_DEFAULT + ","+ROW_PER_PAGE_SECOND+","+ROW_PER_PAGE_THIRD;
    public static final String DEFAULT_PAGINATOR_POSITION = "both";
    public static final String DEFAULT_EMPTY_MESSAGE = "Brak rekordów do wyświetlenia";

    public int getRowPerPageDefault() {
        return ROW_PER_PAGE_DEFAULT;
    }

    public int getRowPerPageSecond() {
        return ROW_PER_PAGE_SECOND;
    }

    public int getRowPerPageThird() {
        return ROW_PER_PAGE_THIRD;
    }

    public String getPaginatorTemplate() {
        return PAGINATOR_TEMPLATE;
    }

    public String getRowsPerPageTemplate() {
        return ROWS_PER_PAGE_TEMPLATE;
    }

    public String getDefaultPaginatorPosition() {
        return DEFAULT_PAGINATOR_POSITION;
    }

    public String getDefaultEmptyMessage() {
        return DEFAULT_EMPTY_MESSAGE;
    }
}
