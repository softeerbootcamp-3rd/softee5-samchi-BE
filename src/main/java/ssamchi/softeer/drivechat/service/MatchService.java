package ssamchi.softeer.drivechat.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.Match;
import ssamchi.softeer.drivechat.dto.request.ConversationDto;
import ssamchi.softeer.drivechat.dto.response.SummaryDto;
import ssamchi.softeer.drivechat.repository.MatchRepository;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    @Transactional
    public SummaryDto conversationSummary(Long matchId, ConversationDto conversation) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid matchId: " + matchId));


        // conversation -> summary 생성 생략
        String summaryResult = "요약요약요.";
        match.makeContentSummary(summaryResult);
        matchRepository.save(match);

        return SummaryDto.builder()
                .contents(summaryResult)
                .build();
    }
}
