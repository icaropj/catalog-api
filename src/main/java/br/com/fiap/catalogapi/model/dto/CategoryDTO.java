package br.com.fiap.catalogapi.model.dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@JGlobalMap
@Getter
@Setter
public class CategoryDTO {

    @Id
    private Long id;

    @NotEmpty
    private String name;

}
