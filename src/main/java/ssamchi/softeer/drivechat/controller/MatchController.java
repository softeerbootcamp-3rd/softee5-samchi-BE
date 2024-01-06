package ssamchi.softeer.drivechat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssamchi.softeer.drivechat.dto.common.ResponseDto;
import ssamchi.softeer.drivechat.dto.request.ConversationRequestDto;
import ssamchi.softeer.drivechat.dto.response.SummaryResponseDto;
import ssamchi.softeer.drivechat.service.MatchService;

@RequestMapping("/api/match")
@RequiredArgsConstructor
@RestController
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/{matchId}/conversation")
    public ResponseEntity<ResponseDto<SummaryResponseDto>> conversationSummary(@PathVariable Long matchId,
                                                                               @RequestBody ConversationRequestDto conversation) {

        SummaryResponseDto summaryResponseDto = matchService.conversationSummary(matchId, conversation);
        ResponseDto<SummaryResponseDto> response = ResponseDto.of(HttpStatus.OK.value(), "Success", summaryResponseDto);
        return ResponseEntity.ok(response);
    }

}
