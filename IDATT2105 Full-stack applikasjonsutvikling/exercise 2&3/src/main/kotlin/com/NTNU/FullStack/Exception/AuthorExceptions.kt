package com.NTNU.FullStack.Exception

import javax.persistence.EntityNotFoundException

class AuthorNotFoundException(errorMessage: String) : EntityNotFoundException(errorMessage)