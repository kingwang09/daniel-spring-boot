package net.freehongs.daniel.support;

import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {
    private boolean result;

    private ResponseEntityData<T> data;

    private Pagination pagination;
}
