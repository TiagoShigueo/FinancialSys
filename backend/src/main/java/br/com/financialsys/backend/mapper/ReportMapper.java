package br.com.financialsys.backend.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.financialsys.backend.dto.CategorySummaryDTO;

@Component
public class ReportMapper {
    public static CategorySummaryDTO toDTO(String category, BigDecimal total) {
        CategorySummaryDTO dto = new CategorySummaryDTO();
        dto.setCategory(category);
        dto.setTotal(total);
        return dto;
    }

    public static List<CategorySummaryDTO> toDTOList(Map<String, BigDecimal> categoryTotals) {
        return categoryTotals.entrySet().stream().map(entry -> toDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
