package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.repository.SelectionRepository;
import com.maria.crudapp_participants.selections.Selection;
import com.maria.crudapp_participants.service.producer.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SelectionServicesImpl implements SelectionService {

    private final Producer producer;
    private final SelectionRepository selectionRepository;


    @Override
    @Transactional
    public Optional<Selection> updateSelectionWithResult(UUID selectionId, String result) {
        Optional<Selection> selection = selectionRepository.findById(selectionId);
        selection.ifPresent(value -> {
            value.setResult(result);
            producer.sendMessage(value);
        });

        return selection;
    }
}
