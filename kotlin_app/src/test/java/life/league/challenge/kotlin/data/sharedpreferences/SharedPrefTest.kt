package life.league.challenge.kotlin.data.sharedpreferences

import android.content.SharedPreferences
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.eq
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class SharedPrefTest {

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var sharedPref: SharedPref

    @Before
    fun setUp() {
        sharedPref = Mockito.spy(SharedPref(sharedPreferences))
    }

    // String
    @Test
    fun `check read() calls getString`() {
        sharedPref.read("key", null)
        verify(sharedPreferences).getString(eq("key"), eq(null))
    }

    @Test
    fun `check write() calls putString`() {
        whenever(sharedPreferences.edit()).thenReturn(editor)
        sharedPref.write("key", "hi")
        inOrder(sharedPreferences, editor, editor) {
            verify(sharedPreferences).edit()
            verify(editor).putString(eq("key"), eq("hi"))
            verify(editor).apply()
        }
    }

    // Boolean
    @Test
    fun `check read() calls getBoolean`() {
        sharedPref.read("key", true)
        verify(sharedPreferences).getBoolean(eq("key"), eq(true))
    }

    @Test
    fun `check write() calls putBoolean`() {
        whenever(sharedPreferences.edit()).thenReturn(editor)
        sharedPref.write("key", true)
        inOrder(sharedPreferences, editor, editor) {
            verify(sharedPreferences).edit()
            verify(editor).putBoolean(eq("key"), eq(true))
            verify(editor).apply()
        }
    }

    // Int
    @Test
    fun `check read() calls getInt`() {
        sharedPref.read("key", 1)
        verify(sharedPreferences).getInt(eq("key"), eq(1))
    }

    @Test
    fun `check write() calls putInt`() {
        whenever(sharedPreferences.edit()).thenReturn(editor)
        sharedPref.write("key", 10)
        inOrder(sharedPreferences, editor, editor) {
            verify(sharedPreferences).edit()
            verify(editor).putInt(eq("key"), eq(10))
            verify(editor).apply()
        }
    }

    // Long
    @Test
    fun `check read() calls getLong`() {
        sharedPref.read("key", 1L)
        verify(sharedPreferences).getLong(eq("key"), eq(1L))
    }

    @Test
    fun `check write() calls putLong`() {
        whenever(sharedPreferences.edit()).thenReturn(editor)
        sharedPref.write("key", 1L)
        inOrder(sharedPreferences, editor, editor) {
            verify(sharedPreferences).edit()
            verify(editor).putLong(eq("key"), eq(1L))
            verify(editor).apply()
        }
    }

    // Remove
    @Test
    fun `check remove() calls remove`() {
        whenever(sharedPreferences.edit()).thenReturn(editor)
        sharedPref.remove("key")
        inOrder(sharedPreferences, editor, editor) {
            verify(sharedPreferences).edit()
            verify(editor).remove(eq("key"))
            verify(editor).apply()
        }
    }


}