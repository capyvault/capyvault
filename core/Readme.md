How the modules communicate
Create secret
SecretController
    ↓
CreateSecretUseCase
    ↓
CreateSecretHandler
    ↓
SecretEncryptionPort
    ↓
LocalSecretEncryptionAdapter
    ↓
SecretPersistencePort
    ↓
SecretRepositoryAdapter
Read secret value
SecretController
    ↓
GetSecretValueUseCase
    ↓
GetSecretValueHandler
    ↓
SecretPersistencePort
    ↓
SecretEncryptionPort
Rotate secret

RotationController
    ↓
ExecuteRotationUseCase
    ↓
ExecuteRotationHandler
    ↓
RotateSecretUseCase
    ↓
RotateSecretHandler
    ↓
SecretEncryptionPort
    ↓
SecretPersistencePort

The important part:

rotation -> secret application port
rotation does not touch secret persistence

That is loose coupling.

## Secret feature
- Create secret
- Store encrypted value only
- Read secret metadata
- Read decrypted secret value
- Update secret value
- Automatic version creation
- Soft delete secret
- Current version tracking

Encryption feature
- AES-256-GCM encryption
- AES-256-GCM decryption
- Key id stored with ciphertext
- Nonce stored with ciphertext
- Algorithm stored with ciphertext
- Easy to replace with Vault/KMS later

Rotation feature
- Create rotation policy
- Manual rotation
- Generated password rotation
- Rotation creates new secret version
- Rotation policy tracks lastRotatedAt
- Rotation policy tracks nextRotationAt