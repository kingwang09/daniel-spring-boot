package net.freehongs.daniel.support;

import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    private Long page;
    private Long totalCount;
}
