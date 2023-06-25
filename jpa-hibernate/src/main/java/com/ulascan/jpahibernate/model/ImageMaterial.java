package com.ulascan.jpahibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class ImageMaterial extends Material{

    @Enumerated(EnumType.STRING)
    @Column(
            name = "type",
            nullable = false
    )
    private ImageType type;

}
