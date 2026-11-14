package com.example.terrariumcare.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class ImageStorageManagerTest {

    private lateinit var context: Context
    private lateinit var imageStorageManager: ImageStorageManager

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        imageStorageManager = ImageStorageManagerImpl(context)
    }

    @Test
    fun saveImageToInternalStorage_returnsNullForBlankUri() {
        val result = imageStorageManager.saveImageToInternalStorage("")
        assertNull(result)
    }

    @Test
    fun deleteImageFromInternalStorage_returnsFalseForNullOrBlankPath() {
        assertFalse(imageStorageManager.deleteImageFromInternalStorage(null))
        assertFalse(imageStorageManager.deleteImageFromInternalStorage(""))
    }

    @Test
    fun deleteImageFromInternalStorage_returnsFalseForNonExistentFile() {
        val nonExistentPath = File(context.filesDir, "non_existent_file.jpg").absolutePath
        val result = imageStorageManager.deleteImageFromInternalStorage(nonExistentPath)
        assertFalse(result)
    }
}
