package org.bklab.flow.components.pagination;


public interface PageSwitchEvent {

    void accept(Integer currentPageNumber, Integer pageSize, Integer lastPageNumber, boolean isFromClient);
}
