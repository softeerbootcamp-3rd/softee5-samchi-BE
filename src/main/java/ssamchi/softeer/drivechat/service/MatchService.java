package ssamchi.softeer.drivechat.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.Match;
import ssamchi.softeer.drivechat.repository.MatchRepository;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    @Transactional
    public String conversationSummary(Long matchId, String conversation) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid matchId: " + matchId));

        match.makeContentSummary(conversation);
        matchRepository.save(match);

        String summaryResult = "요약요약요.";
        return summaryResult;
    }
}
