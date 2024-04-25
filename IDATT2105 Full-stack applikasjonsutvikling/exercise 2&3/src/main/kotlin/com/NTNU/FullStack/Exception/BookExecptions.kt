package com.NTNU.FullStack.Exception

import javax.persistence.EntityNotFoundException

class BookNotFoundExecption(errorMessage: String) : EntityNotFoundException(errorMessage)