package wangyeo.interview.repository

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import wangyeo.interview.repository.api.ContactApi
import wangyeo.interview.repository.model.Contact
import wangyeo.interview.repository.repository.ContactRepository
import wangyeo.interview.repository.repository.impl.ContactRepositoryImpl

class ContactRepositoryTest {
    private lateinit var contactRepository: ContactRepository

    @Mock
    lateinit var contactApi: ContactApi

    @Before
    fun setUp() {
        this.contactApi = Mockito.mock(ContactApi::class.java)
        this.contactRepository = ContactRepositoryImpl(contactApi = this.contactApi)
    }

    @Test
    suspend fun fetchContacts() {
        // When
        val listContact = arrayOf(
            Contact.dummyContact()
        )
        Mockito.`when`(this.contactApi.fetchContacts(
            page = 0,
            perPage = 20,
        )).thenReturn(listContact)

        // Given
        val contacts = this.contactRepository.fetchContacts(
            page = 0,
        )

        // Verify
        assertNotNull(contacts)
        assertEquals(contacts.size, 1)
        assertEquals(contacts[0].id, 99)
    }
}