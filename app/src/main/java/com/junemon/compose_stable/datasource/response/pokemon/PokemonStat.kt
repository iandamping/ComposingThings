package com.junemon.compose_stable.datasource.response.pokemon

import com.google.gson.annotations.SerializedName
import com.junemon.compose_stable.domain.model.Dto
import com.junemon.compose_stable.domain.model.pokemon.PokemonStat
import java.util.*

/**
 * Created by Ian Damping on 08,May,2021
 * Github https://github.com/iandamping
 * Indonesia.
 */

data class PokemonBasicStatsResponse(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("stat") val statName: PokemonStatNameResponse
) : Dto {
    override fun toDomain(): PokemonStat {
        return PokemonStat(
            baseStat, statName.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
        )
    }
}

data class PokemonStatNameResponse(@SerializedName("name") val name: String)