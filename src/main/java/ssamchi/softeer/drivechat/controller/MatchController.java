package ssamchi.softeer.drivechat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssamchi.softeer.drivechat.dto.common.ResponseDto;
import ssamchi.softeer.drivechat.dto.request.ConversationDto;
import ssamchi.softeer.drivechat.dto.response.SummaryDto;
import ssamchi.softeer.drivechat.service.MatchService;

@RequestMapping("/api/match")
@RequiredArgsConstructor
@Controller
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/{matchId}/conversation")
    public ResponseEntity<ResponseDto<SummaryDto>> conversationSummary(@PathVariable Long matchId,
                                                                       @RequestBody ConversationDto conversation) {

        SummaryDto summaryDto = matchService.conversationSummary(matchId, conversation);
        ResponseDto<SummaryDto> response = ResponseDto.of(HttpStatus.OK.value(), "Success", summaryDto);
        return ResponseEntity.ok(response);
    }

}
