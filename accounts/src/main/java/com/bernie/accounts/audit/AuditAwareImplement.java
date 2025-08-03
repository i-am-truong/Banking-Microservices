package com.bernie.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImplement")
public class AuditAwareImplement implements AuditorAware<String> {

    /**
     * Returns the current auditor's username or identifier.
     * @return the current auditor
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS");
    }
}
