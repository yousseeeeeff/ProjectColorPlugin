package com.moutaouakkil.settings

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project

@State(
    name = "ProjectColorSettings",
    storages = [Storage(StoragePathMacros.WORKSPACE_FILE)]
)
class ProjectColorSettings : PersistentStateComponent<ProjectColorSettings.State> {
    class State {
        var colorHex: String? = null
    }

    private var state = State()

    override fun getState(): State = state
    override fun loadState(state: State) {
        this.state = state
    }

    companion object {
        fun getInstance(project: Project): ProjectColorSettings =
            project.service()
    }
}