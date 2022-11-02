package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.selections.Selection;

import java.util.Optional;
import java.util.UUID;

public interface SelectionService {
    Optional<Selection> updateSelectionWithResult(UUID selectionId, String result);
}

