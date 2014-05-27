package nineNineProblems

import org.specs2.mutable.Specification
import org.specs2.specification.{Scope, After, BeforeAfterEach}

object ProblemsSpec extends Specification with problems{

  "last" should {
    "return the last element in a list" in {
      last(List(1, 1, 2, 3, 5, 8)) == 8
    }
  }

  "penumulate" should {
    "find the last but one element of a list" in {
      penultimate(List(1, 1, 2, 3, 5, 8)) === 5
    }
  }

  "nth" should {
    "find the nth element of a list" in {
      nth(2, List(1, 1, 2, 3, 5, 8)) === 2
    }
  }

  "length" should {
    "find the number of elements of a list" in {
      length(List(1, 1, 2, 3, 5, 8)) === 6
    }
  }

  "reverse" should {
    "reverse a list" in {
      reverse(List(1, 1, 2, 3, 5, 8)) === List(8, 5, 3, 2, 1, 1)
    }
  }

  "isPalindrome" should {
    "Find out whether a list is a palindrome" in {
      isPalindrome(List(1, 2, 3, 2, 1)) == true
    }
  }

  "flatten" should {
    "Flatten a nested list structure" in {
      flatten(List(List(1, 1), 2, List(3, List(5, 8)))) === List(1, 1, 2, 3, 5, 8)
    }
  }

  "compress" should {
    "Eliminate consecutive duplicates of list elements" in {
      compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) === List('a, 'b, 'c, 'a, 'd, 'e)
    }
  }

  "pack" should {
    "Pack consecutive duplicates of list elements into sublists" in {
      pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) ===
        List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
    }
  }

  "Run-length encoding of a list" should {
    "encode consecutive duplicates in tuples" in {
      encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) ===
        List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
    }
  }

  "Modified run-length encoding" should {
    "if an element has no duplicates it is simply copied into the result list" in {
      encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) ===
        List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))
    }
  }

  "Decode a run-length encoded list" should {
    "construct its uncompressed version" in {
      decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))) === 
        List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    }
  }

  "encodeDirect" should {
    "run-length encode a list directly" in {
      encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) ===
        List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
    }
  }

  "duplicate" should {
    "duplicate each element in a list" in {
      duplicate(List('a, 'b, 'c, 'c, 'd)) === List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)
    }
  }

  "duplicateN" should {
    "duplicate each element in a list n times" in {
      duplicateN(3, List('a, 'b, 'c, 'c, 'd)) === List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
    }
  }

  "drop" should {
    "Drop every Nth element from a list" in {
      drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) === List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
    }
  }

  "split" should {
    "split a list into two parts" in {
      split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) === (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    }
  }

  "slice" should {
    "Extract a slice from a list" in {
      slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) === List('d, 'e, 'f, 'g)
    }
  }

  "rotate" should {
    "Rotate a list N places to the left" in {
      rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) === List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
    } 
  }
  "rotate" should {
    "Rotate backwards" in {
      rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) === List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)
    }
  }
}