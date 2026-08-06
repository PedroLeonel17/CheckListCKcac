package dev.pedro.CodigoKidChecklist.Dto.ItemChecklistDto;
import dev.pedro.CodigoKidChecklist.Model.ItemChecklist;

public record ItemChecklistDto(
        Long id,
        String nomeAluno,
        String parecer,
        String observacao,
        boolean presente,
        boolean recuperacao,
        boolean intervalo
) {}