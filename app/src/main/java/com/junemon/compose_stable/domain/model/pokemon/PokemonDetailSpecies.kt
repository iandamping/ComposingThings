package com.junemon.compose_stable.domain.model.pokemon

import com.junemon.compose_stable.domain.model.Domain

/**
 * Created by Ian Damping on 10,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */
data class PokemonDetailSpecies(
    val happiness: Int,
    val captureRate: Int,
    val color: String,
    val eggGroup1: String,
    val eggGroup2: String,
    val generation: String,
    val growthRate: String,
    val habitat: String,
    val shape: String,
): Domain