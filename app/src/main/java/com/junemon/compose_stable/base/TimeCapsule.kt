package com.junemon.compose_stable.base

interface TimeCapsule<S : MviUiState> {
    fun addState(state: S)
    fun getStates(): List<S>
}

class TimeTravelCapsule<S : MviUiState>(
    private val onStateSelected: (S) -> Unit
) : TimeCapsule<S> {

    private val states = mutableListOf<S>()

    override fun addState(state: S) {
        states.add(state)
    }
    override fun getStates(): List<S> {
        return states
    }
}