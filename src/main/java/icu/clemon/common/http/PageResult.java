package icu.clemon.common.http;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageResult<T> extends Result<List<T>> implements Serializable {

    private final long totalItems;
    private final long totalPages;
    private final long size;
    private final long page;
    private final long offset;

    public PageResult(List<T> content, Pageable pageable, long total) {
        super();
        this.size = pageable.getPageSize();
        this.page = pageable.getPageNumber();
        this.totalItems = total;
        this.totalPages = (this.totalItems + this.size - 1) / this.size;
        this.offset = (this.page >= this.totalPages) ? this.totalItems:pageable.getOffset();
        this.setData(content);
        this.setCode(ResultCode.CODE200.getCode());
        this.setTs(System.currentTimeMillis());
        this.setMsg("ok");
        this.setSuccess(true);
    }

    public static <T> PageResult<T> of(List<T> content, Pageable pageable, long total) {
        return new PageResult<>( content, pageable, total);
    }

}
