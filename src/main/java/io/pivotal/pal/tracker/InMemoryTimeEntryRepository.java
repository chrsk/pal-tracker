package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private final Map<Long, TimeEntry> inMemoryMap = new HashMap<>();

    @Override
    public TimeEntry create(TimeEntry te) {
        if (!inMemoryMap.isEmpty()){
            Set<Long> mapKeySet = inMemoryMap.keySet();
            Long maxId = Collections.max(mapKeySet);
            te.setId(maxId+1);
        }

        inMemoryMap.put(te.getId(),te);

        return te;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return inMemoryMap.get(timeEntryId);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(inMemoryMap.values());
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry te) {

        TimeEntry removedEntry = inMemoryMap.remove(timeEntryId);

        if(removedEntry == null) {
            return null;
        }

        return this.create(te);
    }

    @Override
    public boolean delete(Long timeEntryId) {
        return inMemoryMap.remove(timeEntryId) == null;
    }
}
