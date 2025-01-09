package dev.jacobandersen.cams.decker;

import org.springframework.data.domain.Page;

import java.util.List;

public class PaginatedResult<T> {
    private final Page<T> page;

    public PaginatedResult(Page<T> page) {
        this.page = page;
    }

    public long getPage() {
        return page.getNumber();
    }

    public long getItemsThisPage() {
        return page.getNumberOfElements();
    }

    public long getTotalPages() {
        return page.getTotalPages();
    }

    public long getTotalItems() {
        return page.getTotalElements();
    }

    public boolean isFirstPage() {
        return page.isFirst();
    }

    public boolean isLastPage() {
        return page.isLast();
    }

    public List<T> getItems() {
        return page.getContent();
    }
}
