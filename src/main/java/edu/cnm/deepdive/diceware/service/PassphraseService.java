package edu.cnm.deepdive.diceware.service;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class PassphraseService implements AbstractPassphraseService {

  private final List<String> words; // DONE: 6/24/25 Add field for abstract word provider.
  private final RandomGenerator rng;

  @Autowired
  PassphraseService(WordProvider wordProvider, RandomGenerator rng) {
    words = new ArrayList<>(wordProvider.provide()); // performant random access
    this.rng = rng;
  }

  @Override
  public List<String> generate(int length) {
    return rng.ints(length, 0, words.size())
        .mapToObj(words::get)
        .toList();
//    return rng.ints(0, words.size())
//        .limit(length)
//        .mapToObj(words::get)
//        .toList();
//    return Stream.generate(() -> words.get(rng.nextInt(words.size()))) // generate takes a supplier (func intf -> get(): no params, and returns T of Supplier<T>)
//        .limit(length)
//        .toList();
  }

}
