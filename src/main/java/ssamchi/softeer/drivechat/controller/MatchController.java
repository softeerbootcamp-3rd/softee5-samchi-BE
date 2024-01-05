package ssamchi.softeer.drivechat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssamchi.softeer.drivechat.dto.common.ResponseDto;
import ssamchi.softeer.drivechat.service.MatchService;

@RequestMapping("/api/match")
@RequiredArgsConstructor
@Controller
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/{matchId}/conversation")
    public ResponseEntity<ResponseDto<String>> conversationSummary(@PathVariable Long matchId,
                                                                   @RequestBody String conversation) {

        String summaryResult = matchService.conversationSummary(matchId, conversation);
        ResponseDto<String> response = ResponseDto.of(200, "Success", summaryResult);
        return ResponseEntity.ok(response);
    }

}
