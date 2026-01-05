package tuanan.test.category.dto;

import java.util.List;
public record CategoryDeleteRequest(
        List<Long> ids
) {
}
