package br.com.viewsystem_sample.local_storage.ext

import br.com.viewsystem_sample.local_storage.db.LocalDatabase
import org.koin.core.scope.Scope

internal val Scope.database: LocalDatabase
    get() = get<LocalDatabase>()