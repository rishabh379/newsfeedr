package com.pvsrishabh.newsfeedr.domain.usecases.app_entry

import com.pvsrishabh.newsfeedr.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}