package com.auth0.jwtdecodejava.impl;

import com.auth0.jwtdecodejava.interfaces.Claim;
import com.auth0.jwtdecodejava.interfaces.Payload;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.istack.internal.NotNull;

import java.util.Date;
import java.util.Map;

import static com.auth0.jwtdecodejava.impl.ClaimImpl.extractClaim;
import static com.auth0.jwtdecodejava.impl.Claims.*;

public class PayloadImpl implements Payload {
    private Map<String, JsonNode> tree;

    public PayloadImpl(Map<String, JsonNode> tree) {
        this.tree = tree;
    }

    @Override
    public String getIssuer() {
        return extractClaim(ISSUER, tree).asString();
    }

    @Override
    public String getSubject() {
        return extractClaim(SUBJECT, tree).asString();
    }

    @Override
    public String[] getAudience() {
        try {
            return extractClaim(AUDIENCE, tree).asArray(String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[]{};
    }

    @Override
    public Date getExpiresAt() {
        return extractClaim(EXPIRES_AT, tree).asDate();
    }

    @Override
    public Date getNotBefore() {
        return extractClaim(NOT_BEFORE, tree).asDate();
    }

    @Override
    public Date getIssuedAt() {
        return extractClaim(ISSUED_AT, tree).asDate();
    }

    @Override
    public String getId() {
        return extractClaim(JWT_ID, tree).asString();
    }

    @Override
    public Claim getClaim(@NotNull String name) {
        return extractClaim(name, tree);
    }

}