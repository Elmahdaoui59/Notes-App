package com.eldevs.notesapp.feature_note.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.eldevs.notesapp.di.AppModule
import com.eldevs.notesapp.feature_note.presentation.uttil.TestTags.CONTENT_TEXT_FIELD
import com.eldevs.notesapp.feature_note.presentation.uttil.TestTags.TITLE_TEXT_FIELD
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun saveNewNote_editAfterwards(){
        composeRule.onNodeWithContentDescription("Add note").performClick()
        composeRule.onNodeWithTag(TITLE_TEXT_FIELD).performTextInput("first-note")
        composeRule.onNodeWithTag(CONTENT_TEXT_FIELD).performTextInput("first note content")
        composeRule.onNodeWithContentDescription("save note").performClick()
        composeRule.onNodeWithText("first-note").assertIsDisplayed()

        composeRule.onNodeWithText("first-note").performClick()
        composeRule.onNodeWithTag(TITLE_TEXT_FIELD).assertTextEquals("first-note")
        composeRule.onNodeWithTag(CONTENT_TEXT_FIELD).assertTextEquals("first note content")

        composeRule.onNodeWithTag(TITLE_TEXT_FIELD).performTextInput("update")
        composeRule.onNodeWithContentDescription("save note").performClick()
        composeRule.onNodeWithText("update").assertIsDisplayed()

    }
}