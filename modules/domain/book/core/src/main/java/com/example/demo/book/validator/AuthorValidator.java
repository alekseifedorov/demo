package com.example.demo.book.validator;

import com.example.demo.common.error.ErrorCode;
import com.example.demo.common.exception.PlatformException;
import com.example.demo.domain.book.model.Author;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorValidator {


    public void validate(Author author) {
        validateName(author);
    }

    private void validateName(Author author) {
        if (author.getName().startsWith("aaa")) {
            throw new PlatformException(ErrorCode.VALIDATION_AUTHOR_NAME_NOT_VALID, new Object[] {author.getName()});
        }
    }
}
