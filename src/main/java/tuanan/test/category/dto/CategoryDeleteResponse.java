package tuanan.test.category.dto;
import java.util.List;

public record CategoryDeleteResponse(
        List<Long> deleteIds
) {
}
