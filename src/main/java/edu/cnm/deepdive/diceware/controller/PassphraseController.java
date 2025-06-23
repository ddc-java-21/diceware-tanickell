package edu.cnm.deepdive.diceware.controller;

import edu.cnm.deepdive.diceware.service.AbstractPassphraseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passphrases")
public class PassphraseController {

  private final AbstractPassphraseService passphraseService;

  @Autowired
  public PassphraseController(AbstractPassphraseService passphraseService) {
    this.passphraseService = passphraseService;
  }

  @GetMapping(path = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<String> generate(@RequestParam(required = false, defaultValue = "5") int length) {
    return passphraseService.generate(length);
  }

}
