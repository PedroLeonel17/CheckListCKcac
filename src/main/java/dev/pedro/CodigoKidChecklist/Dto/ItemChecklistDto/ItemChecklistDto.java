package dev.pedro.CodigoKidChecklist.Dto.ItemChecklistDto;
import dev.pedro.CodigoKidChecklist.Model.ItemChecklist;

public record ItemChecklistDto(
        Long id,
        String nomeAluno,
        boolean presente,
        String observacao
) {}
