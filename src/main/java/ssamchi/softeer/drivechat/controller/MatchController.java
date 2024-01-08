package ssamchi.softeer.drivechat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssamchi.softeer.drivechat.dto.common.ResponseDto;
import ssamchi.softeer.drivechat.dto.request.BoardRequestRequestDto;
import ssamchi.softeer.drivechat.dto.request.ConversationRequestDto;
import ssamchi.softeer.drivechat.dto.request.RequestMakeMatchingDto;
import ssamchi.softeer.drivechat.dto.response.BoardRequestResponseDto;
import ssamchi.softeer.drivechat.dto.response.ResponseMakeMatchingDto;
import ssamchi.softeer.drivechat.dto.response.ResponseMatchCheckDto;
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

    @PostMapping("/register")
    public ResponseEntity<ResponseDto<ResponseMakeMatchingDto>> makeMatching
            (
                    @RequestBody RequestMakeMatchingDto requestMakeMatchingDto
            )
    {
        ResponseMakeMatchingDto responseMakeMatchingDto = matchService.makeMatching(requestMakeMatchingDto);
        return ResponseEntity.ok()
                .body(ResponseDto.of(HttpStatus.OK.value(), "match created", responseMakeMatchingDto));
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<ResponseDto<ResponseMatchCheckDto>> checkMatching
            (
                @PathVariable Long driverId
            )
    {
        ResponseMatchCheckDto responseMatchCheckDto = matchService.checkMatching(driverId);
        return ResponseEntity.ok()
                .body(ResponseDto.of(HttpStatus.OK.value(), "match ", responseMatchCheckDto));
    }

    @PostMapping("/request")
    public ResponseEntity<ResponseDto<BoardRequestResponseDto>> boardRequest
            (
                    @RequestBody BoardRequestRequestDto boardRequestRequestDto
            )
    {
        BoardRequestResponseDto boardRequestResponseDto = matchService.boardRequest(boardRequestRequestDto);
        return ResponseEntity.ok()
                .body(ResponseDto.of(HttpStatus.OK.value(), "탑승 요청에 성공하였습니다.", boardRequestResponseDto));
    }
}
