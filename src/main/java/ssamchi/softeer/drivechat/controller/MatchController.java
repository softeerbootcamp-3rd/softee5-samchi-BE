package ssamchi.softeer.drivechat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssamchi.softeer.drivechat.dto.common.ResponseDto;
import ssamchi.softeer.drivechat.dto.request.BoardRequestRequestDto;
import ssamchi.softeer.drivechat.dto.request.ConversationRequestDto;
import ssamchi.softeer.drivechat.dto.request.RequestConfirmMatchingDto;
import ssamchi.softeer.drivechat.dto.response.*;
import ssamchi.softeer.drivechat.service.MatchService;

@Tag(name = "Match 관련 컨트롤러", description = "드라이브 챗 Matching 관련 API 목록입니다.")
@RequestMapping("/api/match")
@RequiredArgsConstructor
@RestController
public class MatchController {

    private final MatchService matchService;

    @Operation(summary = "드라이브 챗 종료 후 내용 요약 API", description = "드라이브 챗이 종료된 후 내용을 요약해주는 API 입니다.")
    @PostMapping("/{matchId}/conversation")
    public ResponseEntity<ResponseDto<SummaryResponseDto>> conversationSummary(@PathVariable Long matchId,
                                                                               @RequestBody ConversationRequestDto conversation) {

        SummaryResponseDto summaryResponseDto = matchService.conversationSummary(matchId, conversation);
        ResponseDto<SummaryResponseDto> response = ResponseDto.of(HttpStatus.OK.value(), "Success", summaryResponseDto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "드라이브 챗 매칭 확정 API", description = "드라이브 챗 매칭 확정 API 입니다.")
    @PutMapping("/confirm")
    public ResponseEntity<ResponseDto<ResponseConfirmMatchingDto>> matchingConfirm
            (
                    @RequestBody RequestConfirmMatchingDto requestConfirmMatchingDto
            )
    {
        ResponseConfirmMatchingDto responseConfirmMatchingDto = matchService.confirmMatchCheck(requestConfirmMatchingDto);
        return ResponseEntity.ok()
                .body(ResponseDto.of(HttpStatus.OK.value(), "match confirmed",
                    responseConfirmMatchingDto));
    }

    @Operation(summary = "동승자로부터 탑승 요청이 들어왔는지 확인하는 API", description = "N초마다 동승자로부터 탑승 요청이 들어왔는지 확인하는 API 입니다.")
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<ResponseDto<ResponseCheckMatchRequestedDto>> checkGuestRequested
            (
                @PathVariable Long driverId
            )
    {
        ResponseCheckMatchRequestedDto responseCheckMatchRequestedDto = matchService.checkMatchRequestedByGuest(driverId);
        return ResponseEntity.ok()
                .body(ResponseDto.of(HttpStatus.OK.value(), "check match requested by guest", responseCheckMatchRequestedDto));
    }

    @Operation(summary = "탑승 요청이 운전자에 의해 승인되었는지 확인하는 API", description = "N초마다 탑승 요청이 운전자에 의해 승인되었는지 확인하는 API 입니다.")
    @GetMapping("/{matchId}")
    public ResponseEntity<ResponseDto<ResponseCheckMatchConfirmedDto>> checkMatchConfirmed
            (
                    @PathVariable Long matchId
            )
    {
        ResponseCheckMatchConfirmedDto responseCheckMatchConfirmedDto = matchService.checkMatchConfirmed(matchId);
        return ResponseEntity.ok()
                .body(ResponseDto.of(HttpStatus.OK.value(), "check match is confirmed", responseCheckMatchConfirmedDto));
    }


    @Operation(summary = "드라이브 챗 신청 API", description = "상대방에게 드라이브 챗을 신청하는 API 입니다.")
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
