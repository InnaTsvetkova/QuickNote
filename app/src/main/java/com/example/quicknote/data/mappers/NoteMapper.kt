package com.example.quicknote.data.mappers

import com.example.quicknote.data.NoteEntity
import com.example.quicknote.domain.Note
import javax.inject.Inject

class NoteMapper @Inject constructor(

) : BaseMapper<NoteEntity, Note>() {
    override fun transform(entity: NoteEntity) = Note(
        entity.id,
        entity.text
    )

}