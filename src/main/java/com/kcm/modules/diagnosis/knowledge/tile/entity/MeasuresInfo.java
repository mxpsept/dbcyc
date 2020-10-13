package com.kcm.modules.diagnosis.knowledge.tile.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MEASURES_INFO
 * @author lucky
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeasuresInfo implements Serializable {
    private String primaryId;

    private String diagnosticResults;

    private String measure;

    private static final long serialVersionUID = 1L;
}