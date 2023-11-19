package max.dev.portfolioapi.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MailDTO {

    private String subject;
    private String body;
    private String toEmail;
    private String fromEmail;

}
