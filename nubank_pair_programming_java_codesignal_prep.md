# Nubank Pair Programming Interview Prep Guide — Java / CodeSignal / Cosmo

Candidate: Sangchual Cha  
Interview: Nubank Pair Programming Session  
Format: 90-minute live coding session on CodeSignal with two Nubank engineers  
Recommended language: Java  
AI tool: CodeSignal Cosmo

---

## 1. Interview Goal

The invitation says Nubank wants to understand:

- Problem-solving process
- Code organization
- Testing mindset
- Collaboration and communication
- How you architect a solution while coding
- How you use AI tools such as Cosmo productively

This is not only a LeetCode speed test. It is closer to a practical backend pair-programming exercise where the problem may evolve step by step.

You should show that you can:

1. Clarify requirements.
2. Start with a simple working solution.
3. Write clean, readable Java.
4. Handle edge cases.
5. Explain decisions while coding.
6. Use tests or manual examples to validate behavior.
7. Accept feedback and adapt.
8. Use Cosmo as an assistant, not as a replacement for your own reasoning.

---

## 2. Best Language Choice

Use **Java**.

Why Java is a good choice:

- You are strongest in Java/Kotlin backend development.
- Java lets you show clean domain modeling.
- Financial/backend problems are easier to express with classes, enums, records, maps, and services.
- You can demonstrate Staff/Principal-level thinking while still coding hands-on.

Recommended Java features:

```java
record Transaction(String id, String accountId, long amountCents) {}
enum TransactionType { DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT }

Map<String, Account> accounts = new HashMap<>();
Deque<Long> timestamps = new ArrayDeque<>();
PriorityQueue<Item> heap = new PriorityQueue<>();
```

Avoid Spring Boot or framework-heavy code. This is an algorithm and design coding interview.

---

## 3. Opening Script

Use this at the beginning:

```text
I’ll use Java today. I’ll first clarify the requirements and edge cases, then implement a simple working solution. After that, I’ll add validations, tests or manual examples, and improve the design if new requirements come in. I may use Cosmo for edge cases, test ideas, and code review, but I’ll explain and own the final solution.
```

This shows language confidence, structured problem solving, testing mindset, responsible AI usage, and collaboration.

---

## 4. How to Use Cosmo During the Interview

Because Nubank explicitly encourages AI usage, use Cosmo — but in a controlled, professional way.

### Good AI Usage

Use Cosmo for:

- Requirement clarification
- Edge-case discovery
- Test case generation
- Code review
- Refactoring suggestions
- Syntax reminders
- Complexity checking

### Bad AI Usage

Avoid asking:

```text
Solve the whole problem for me.
Generate the entire solution.
What is the answer?
```

That makes you look passive.

### Strong Cosmo Usage Pattern

```text
Human: clarify requirements and choose direction
Cosmo: suggest edge cases or tests
Human: decide which ones matter
Human: implement
Cosmo: review for bugs
Human: accept or reject suggestions and explain why
```

### Useful Cosmo Prompts

#### Edge Cases

```text
Given this problem statement, list important edge cases I should test. Do not solve the implementation.
```

#### Design Options

```text
Suggest two simple Java design options for this problem. Keep them interview-friendly and avoid overengineering.
```

#### Test Ideas

```text
Generate concise test cases for the core behavior and edge cases. Use Java-style test names.
```

#### Code Review

```text
Review this implementation for correctness, missed edge cases, and readability. Do not rewrite everything; give concise suggestions.
```

#### Debugging

```text
This test is failing. Based on the code below, identify the likely bug and suggest the smallest fix.
```

#### Complexity

```text
What is the time and space complexity of this implementation?
```

### What to Say After Using Cosmo

```text
Cosmo suggested these edge cases. I think the most important ones for correctness are duplicate requests, invalid amounts, and insufficient balance. I’ll handle those first.
```

```text
Cosmo suggested two designs. I’ll choose the simpler one because this is an in-memory interview problem and we can evolve it if needed.
```

```text
Cosmo pointed out a possible partial mutation in transfer. That is a good catch. I’ll validate both accounts before mutating balances.
```

---

## 5. 90-Minute Interview Time Management

| Time | Action |
|---|---|
| 0–5 min | Read problem, restate requirements, ask clarifying questions |
| 5–10 min | Identify core data model and edge cases |
| 10–15 min | Ask Cosmo for edge cases/test ideas if useful |
| 15–40 min | Implement simple working solution |
| 40–55 min | Add validations and error handling |
| 55–70 min | Add tests/manual examples and fix bugs |
| 70–80 min | Handle follow-up requirement |
| 80–87 min | Ask Cosmo/code review, polish |
| 87–90 min | Summarize complexity and trade-offs |

Start simple, then improve. Do not try to build a perfect solution first.

---

## 6. Core Java Data Structures to Review

### HashMap

Use for counting, lookup, idempotency, account storage.

```java
Map<String, Integer> counts = new HashMap<>();
counts.put(key, counts.getOrDefault(key, 0) + 1);
```

### HashSet

Use for deduplication.

```java
Set<String> seen = new HashSet<>();
if (!seen.add(eventId)) {
    return; // duplicate
}
```

### ArrayDeque

Use for sliding window, queues, recent events.

```java
Deque<Long> timestamps = new ArrayDeque<>();
timestamps.offerLast(now);
timestamps.pollFirst();
```

### PriorityQueue

Use for top K.

```java
PriorityQueue<Map.Entry<String, Integer>> heap =
    new PriorityQueue<>(Map.Entry.comparingByValue());
```

### Comparator

Use for sorting with tie-breakers.

```java
entries.sort((a, b) -> {
    int cmp = Integer.compare(b.getValue(), a.getValue());
    if (cmp != 0) return cmp;
    return a.getKey().compareTo(b.getKey());
});
```

---

# 7. Expected Question 1: Wallet / Account / Ledger Service

## Example Prompt

```text
Implement an in-memory wallet service.

Operations:
- createWallet(walletId)
- deposit(walletId, amount, requestId)
- withdraw(walletId, amount, requestId)
- transfer(fromWalletId, toWalletId, amount, requestId)
- getBalance(walletId)
- getTransactions(walletId)

Rules:
- Amount must be positive
- Wallet must exist
- Withdrawal cannot overdraft
- requestId must be idempotent
- Transfer creates debit and credit records
```

## Why This Is Likely

Nubank is a fintech company. Wallet, account, ledger, transfer, idempotency, and transaction history are highly relevant.

## Clarifying Questions

```text
Should invalid input throw an exception or return an error result?
Should duplicate requestId return the previous result?
Can transfer to the same wallet happen?
Should transaction history include failed attempts?
Are amounts represented in cents?
Is this single-threaded in-memory for the interview?
```

## Recommended Design

```text
WalletService
- Map<String, Wallet> wallets
- Map<String, OperationResult> processedRequests

Wallet
- walletId
- balanceCents
- List<Transaction>

Transaction
- transactionId
- walletId
- type
- amountCents
- relatedWalletId
```

## Java Solution Sketch

```java
import java.util.*;

public class WalletService {

    private final Map<String, Wallet> wallets = new HashMap<>();
    private final Map<String, OperationResult> processedRequests = new HashMap<>();

    public boolean createWallet(String walletId) {
        if (walletId == null || walletId.isBlank()) {
            return false;
        }
        if (wallets.containsKey(walletId)) {
            return false;
        }
        wallets.put(walletId, new Wallet(walletId));
        return true;
    }

    public OperationResult deposit(String walletId, long amountCents, String requestId) {
        if (processedRequests.containsKey(requestId)) {
            return processedRequests.get(requestId);
        }

        Wallet wallet = wallets.get(walletId);
        if (wallet == null || amountCents <= 0) {
            return save(requestId, OperationResult.failed("INVALID_REQUEST"));
        }

        wallet.balanceCents += amountCents;
        wallet.transactions.add(new Transaction(UUID.randomUUID().toString(), walletId, TransactionType.DEPOSIT, amountCents));

        return save(requestId, OperationResult.success(wallet.balanceCents));
    }

    public OperationResult withdraw(String walletId, long amountCents, String requestId) {
        if (processedRequests.containsKey(requestId)) {
            return processedRequests.get(requestId);
        }

        Wallet wallet = wallets.get(walletId);
        if (wallet == null || amountCents <= 0) {
            return save(requestId, OperationResult.failed("INVALID_REQUEST"));
        }

        if (wallet.balanceCents < amountCents) {
            return save(requestId, OperationResult.failed("INSUFFICIENT_FUNDS"));
        }

        wallet.balanceCents -= amountCents;
        wallet.transactions.add(new Transaction(UUID.randomUUID().toString(), walletId, TransactionType.WITHDRAWAL, -amountCents));

        return save(requestId, OperationResult.success(wallet.balanceCents));
    }

    public OperationResult transfer(String fromWalletId, String toWalletId, long amountCents, String requestId) {
        if (processedRequests.containsKey(requestId)) {
            return processedRequests.get(requestId);
        }

        if (amountCents <= 0 || fromWalletId.equals(toWalletId)) {
            return save(requestId, OperationResult.failed("INVALID_REQUEST"));
        }

        Wallet from = wallets.get(fromWalletId);
        Wallet to = wallets.get(toWalletId);

        if (from == null || to == null) {
            return save(requestId, OperationResult.failed("WALLET_NOT_FOUND"));
        }

        if (from.balanceCents < amountCents) {
            return save(requestId, OperationResult.failed("INSUFFICIENT_FUNDS"));
        }

        from.balanceCents -= amountCents;
        to.balanceCents += amountCents;

        from.transactions.add(new Transaction(UUID.randomUUID().toString(), fromWalletId, TransactionType.TRANSFER_OUT, -amountCents));
        to.transactions.add(new Transaction(UUID.randomUUID().toString(), toWalletId, TransactionType.TRANSFER_IN, amountCents));

        return save(requestId, OperationResult.success(from.balanceCents));
    }

    public long getBalance(String walletId) {
        Wallet wallet = wallets.get(walletId);
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet not found");
        }
        return wallet.balanceCents;
    }

    public List<Transaction> getTransactions(String walletId) {
        Wallet wallet = wallets.get(walletId);
        if (wallet == null) {
            return List.of();
        }
        return List.copyOf(wallet.transactions);
    }

    private OperationResult save(String requestId, OperationResult result) {
        if (requestId != null) {
            processedRequests.put(requestId, result);
        }
        return result;
    }

    private static class Wallet {
        private final String walletId;
        private long balanceCents;
        private final List<Transaction> transactions = new ArrayList<>();

        private Wallet(String walletId) {
            this.walletId = walletId;
        }
    }

    record Transaction(String id, String walletId, TransactionType type, long amountCents) {}

    enum TransactionType {
        DEPOSIT,
        WITHDRAWAL,
        TRANSFER_IN,
        TRANSFER_OUT
    }

    record OperationResult(boolean success, String errorCode, long balanceCents) {
        static OperationResult success(long balanceCents) {
            return new OperationResult(true, null, balanceCents);
        }

        static OperationResult failed(String errorCode) {
            return new OperationResult(false, errorCode, 0);
        }
    }
}
```

## Key Talking Points

```text
For money, I use long cents instead of double.
I validate everything before mutation, especially in transfer.
Idempotency is a first-class requirement because retries are normal.
This is an in-memory implementation; in production I would use a durable ledger and database transaction.
```

## Possible Follow-Ups

- Add daily transfer limit
- Add transaction reversal
- Add top N wallets by outgoing transfer amount
- Add transaction statement by date range
- Add pending/settled status
- Add concurrency control

---

# 8. Expected Question 2: Payment Authorization Rules

## Example Prompt

```text
Implement a payment authorization engine.

Input:
- accountId
- merchantId
- amount
- timestamp
- transactionId

Rules:
- Reject if account does not exist
- Reject if amount is not positive
- Reject if balance is insufficient
- Reject duplicate transactionId
- Reject if user exceeds daily spending limit
- Otherwise approve and reduce available balance
```

## Approach

1. Validate transaction ID.
2. Validate account.
3. Validate amount.
4. Check duplicate transaction ID.
5. Check balance.
6. Check daily limit.
7. Approve and update state.

## Java Skeleton

```java
import java.time.*;
import java.util.*;

public class PaymentAuthorizationService {

    private final Map<String, Account> accounts = new HashMap<>();
    private final Set<String> processedTransactionIds = new HashSet<>();
    private final Map<String, Map<LocalDate, Long>> dailySpend = new HashMap<>();

    public AuthResult authorize(TransactionRequest request) {
        if (request == null || request.transactionId() == null) {
            return AuthResult.declined(DeclineReason.INVALID_REQUEST);
        }

        if (!processedTransactionIds.add(request.transactionId())) {
            return AuthResult.declined(DeclineReason.DUPLICATE_TRANSACTION);
        }

        Account account = accounts.get(request.accountId());
        if (account == null) {
            return AuthResult.declined(DeclineReason.ACCOUNT_NOT_FOUND);
        }

        if (request.amountCents() <= 0) {
            return AuthResult.declined(DeclineReason.INVALID_AMOUNT);
        }

        if (account.availableBalanceCents < request.amountCents()) {
            return AuthResult.declined(DeclineReason.INSUFFICIENT_FUNDS);
        }

        LocalDate date = request.timestamp().atZone(ZoneOffset.UTC).toLocalDate();
        Map<LocalDate, Long> accountSpend =
            dailySpend.computeIfAbsent(request.accountId(), k -> new HashMap<>());

        long todaySpend = accountSpend.getOrDefault(date, 0L);
        if (todaySpend + request.amountCents() > account.dailyLimitCents) {
            return AuthResult.declined(DeclineReason.DAILY_LIMIT_EXCEEDED);
        }

        account.availableBalanceCents -= request.amountCents();
        accountSpend.put(date, todaySpend + request.amountCents());

        return AuthResult.approved();
    }

    record TransactionRequest(
        String transactionId,
        String accountId,
        String merchantId,
        long amountCents,
        Instant timestamp
    ) {}

    static class Account {
        String accountId;
        long availableBalanceCents;
        long dailyLimitCents;
    }

    record AuthResult(boolean approved, DeclineReason reason) {
        static AuthResult approved() {
            return new AuthResult(true, null);
        }

        static AuthResult declined(DeclineReason reason) {
            return new AuthResult(false, reason);
        }
    }

    enum DeclineReason {
        INVALID_REQUEST,
        DUPLICATE_TRANSACTION,
        ACCOUNT_NOT_FOUND,
        INVALID_AMOUNT,
        INSUFFICIENT_FUNDS,
        DAILY_LIMIT_EXCEEDED
    }
}
```

## Follow-Ups

- Add fraud velocity checks
- Add per-merchant spending limit
- Store approved and declined transactions
- Return decline reasons
- Make duplicate transaction return original result instead of decline

---

# 9. Expected Question 3: Sliding Window Rate Limiter

## Example Prompt

```text
Implement a rate limiter.
Each user can make at most N requests in a rolling W-second window.
```

## Java Solution

```java
import java.util.*;

public class SlidingWindowRateLimiter {

    private final int maxRequests;
    private final long windowMillis;
    private final Map<String, Deque<Long>> requestsByUser = new HashMap<>();

    public SlidingWindowRateLimiter(int maxRequests, long windowMillis) {
        if (maxRequests <= 0 || windowMillis <= 0) {
            throw new IllegalArgumentException("maxRequests and windowMillis must be positive");
        }

        this.maxRequests = maxRequests;
        this.windowMillis = windowMillis;
    }

    public boolean allow(String userId, long currentTimeMillis) {
        if (userId == null || userId.isBlank()) {
            return false;
        }

        Deque<Long> timestamps =
            requestsByUser.computeIfAbsent(userId, key -> new ArrayDeque<>());

        long cutoff = currentTimeMillis - windowMillis;

        while (!timestamps.isEmpty() && timestamps.peekFirst() <= cutoff) {
            timestamps.pollFirst();
        }

        if (timestamps.size() >= maxRequests) {
            return false;
        }

        timestamps.offerLast(currentTimeMillis);
        return true;
    }
}
```

## Complexity

```text
Amortized time: O(1) per request
Space: O(active users * max requests per window)
```

## Follow-Ups

- Return remaining quota
- Return retryAfterMillis
- Add global limit
- Add per-tier limits
- Make it thread-safe

---

# 10. Expected Question 4: Transaction Reconciliation

## Example Prompt

```text
Given internal transactions and external bank transactions, reconcile them.

Return:
- matched
- missingInternal
- missingExternal
- amountMismatch
```

## Java Solution Sketch

```java
import java.time.*;
import java.util.*;

public class ReconciliationService {

    public ReconciliationResult reconcile(List<Txn> internal, List<Txn> external) {
        Map<String, Txn> internalByRef = new HashMap<>();
        Map<String, Txn> externalByRef = new HashMap<>();

        for (Txn txn : internal) {
            internalByRef.put(txn.referenceId(), txn);
        }

        for (Txn txn : external) {
            externalByRef.put(txn.referenceId(), txn);
        }

        List<TxnPair> matched = new ArrayList<>();
        List<Txn> missingInternal = new ArrayList<>();
        List<Txn> missingExternal = new ArrayList<>();
        List<TxnPair> amountMismatch = new ArrayList<>();

        for (Map.Entry<String, Txn> entry : internalByRef.entrySet()) {
            String ref = entry.getKey();
            Txn internalTxn = entry.getValue();
            Txn externalTxn = externalByRef.get(ref);

            if (externalTxn == null) {
                missingExternal.add(internalTxn);
                continue;
            }

            if (internalTxn.amountCents() != externalTxn.amountCents()) {
                amountMismatch.add(new TxnPair(internalTxn, externalTxn));
            } else {
                matched.add(new TxnPair(internalTxn, externalTxn));
            }
        }

        for (Map.Entry<String, Txn> entry : externalByRef.entrySet()) {
            if (!internalByRef.containsKey(entry.getKey())) {
                missingInternal.add(entry.getValue());
            }
        }

        return new ReconciliationResult(matched, missingInternal, missingExternal, amountMismatch);
    }

    record Txn(String id, String referenceId, long amountCents, LocalDate date) {}
    record TxnPair(Txn internalTxn, Txn externalTxn) {}

    record ReconciliationResult(
        List<TxnPair> matched,
        List<Txn> missingInternal,
        List<Txn> missingExternal,
        List<TxnPair> amountMismatch
    ) {}
}
```

## Follow-Ups

- Duplicate reference IDs
- Fuzzy date matching
- Match by amount + date when reference ID is missing
- Partial matches
- Manual review queue

## Strong Talking Point

```text
In financial systems, reconciliation is part of correctness, not an afterthought. Every mismatch should be explainable, traceable, and replayable.
```

---

# 11. Expected Question 5: Top K / Ranking

## Example Prompt

```text
Given a list of transactions, return top K merchants by transaction volume.
If tied, sort merchant ID ascending.
```

## Java Solution

```java
import java.util.*;

public class TopKMerchants {

    public List<Result> topK(List<Transaction> transactions, int k) {
        if (transactions == null || k <= 0) {
            return List.of();
        }

        Map<String, Long> volumeByMerchant = new HashMap<>();

        for (Transaction tx : transactions) {
            volumeByMerchant.put(
                tx.merchantId(),
                volumeByMerchant.getOrDefault(tx.merchantId(), 0L) + tx.amountCents()
            );
        }

        return volumeByMerchant.entrySet()
            .stream()
            .sorted((a, b) -> {
                int cmp = Long.compare(b.getValue(), a.getValue());
                if (cmp != 0) return cmp;
                return a.getKey().compareTo(b.getKey());
            })
            .limit(k)
            .map(e -> new Result(e.getKey(), e.getValue()))
            .toList();
    }

    record Transaction(String merchantId, long amountCents) {}
    record Result(String merchantId, long totalVolumeCents) {}
}
```

## Complexity

```text
Time: O(n + m log m)
Space: O(m)

n = transactions
m = unique merchants
```

## Optimization Follow-Up

If k is small:

```text
Use a min heap for O(n + m log k)
```

---

# 12. Expected Question 6: In-Memory Event Store

## Example Prompt

```text
Build an append-only event store.

Operations:
- append(accountId, event)
- getEvents(accountId)
- replayBalance(accountId)
```

## Java Solution Sketch

```java
import java.util.*;

public class EventStore {

    private final Map<String, List<LedgerEvent>> eventsByAccount = new HashMap<>();
    private final Set<String> eventIds = new HashSet<>();

    public boolean append(String accountId, LedgerEvent event) {
        if (accountId == null || event == null || event.eventId() == null) {
            return false;
        }

        if (!eventIds.add(event.eventId())) {
            return false;
        }

        eventsByAccount.computeIfAbsent(accountId, k -> new ArrayList<>()).add(event);
        return true;
    }

    public List<LedgerEvent> getEvents(String accountId) {
        return List.copyOf(eventsByAccount.getOrDefault(accountId, List.of()));
    }

    public long replayBalance(String accountId) {
        long balance = 0;

        for (LedgerEvent event : eventsByAccount.getOrDefault(accountId, List.of())) {
            balance += event.amountCents();
        }

        return balance;
    }

    record LedgerEvent(String eventId, long amountCents, long timestampMillis) {}
}
```

## Follow-Ups

- Snapshot balances
- Query by date range
- Event versioning
- Reverse an event
- Idempotent append
- Projection table

---

# 13. Expected Question 7: Rule Engine / Validation Pipeline

## Example Prompt

```text
Implement a transaction validation engine.

Rules:
- amount > 0
- account exists
- balance sufficient
- merchant not blocked
- daily limit not exceeded
```

## Java Sketch

```java
import java.util.*;

public class RuleEngine {

    private final List<Rule> rules;

    public RuleEngine(List<Rule> rules) {
        this.rules = rules;
    }

    public ValidationResult validate(Transaction tx, Context context) {
        for (Rule rule : rules) {
            Optional<String> error = rule.validate(tx, context);
            if (error.isPresent()) {
                return ValidationResult.failed(error.get());
            }
        }

        return ValidationResult.success();
    }

    interface Rule {
        Optional<String> validate(Transaction tx, Context context);
    }

    record Transaction(String accountId, String merchantId, long amountCents) {}
    record Context(Set<String> blockedMerchants, Map<String, Long> balances) {}

    record ValidationResult(boolean valid, String errorCode) {
        static ValidationResult success() {
            return new ValidationResult(true, null);
        }

        static ValidationResult failed(String errorCode) {
            return new ValidationResult(false, errorCode);
        }
    }
}
```

## Staff-Level Talking Point

```text
A rule pipeline keeps the code extensible and testable. For production, I would also version rules and log which rule produced the decision.
```

---

# 14. Classic Java DSA Questions to Prepare

Even though Nubank may prefer practical problems, still prepare these:

1. Two Sum
2. Group Anagrams
3. LRU Cache
4. Merge Intervals
5. Top K Frequent Elements
6. Longest Substring Without Repeating Characters
7. Valid Parentheses
8. Merge K Sorted Lists
9. Kth Largest Element
10. Sliding Window Maximum

Highest priority:

```text
HashMap counting
Top K
Sliding window
LRU cache
Interval merging
In-memory service design
```

---

# 15. Manual Test Examples

## Wallet

```text
create A
create B
deposit A 1000 req1 => success, A=1000
withdraw A 300 req2 => success, A=700
transfer A to B 500 req3 => success, A=200, B=500
repeat req3 => should not transfer again
withdraw A 300 req4 => insufficient funds
```

## Rate Limiter

```text
limit = 3 requests / 10 seconds

u1 at 1000 => true
u1 at 2000 => true
u1 at 3000 => true
u1 at 4000 => false
u1 at 12000 => true
u2 at 4000 => true
```

## Reconciliation

```text
internal: ref1 $100, ref2 $200, ref3 $300
external: ref1 $100, ref2 $250, ref4 $400

matched: ref1
amountMismatch: ref2
missingExternal: ref3
missingInternal: ref4
```

---

# 16. What Interviewers May Challenge

## Why not use double for money?

```text
I would avoid double because floating-point arithmetic can introduce rounding errors. I’ll use long cents for this exercise.
```

## What about concurrency?

```text
This in-memory implementation assumes single-threaded execution. If multiple threads access it, I would add synchronization, optimistic locking, or serialize commands by account ID.
```

## What if duplicate request is retried?

```text
I store the requestId with the original result. If the same requestId appears again, I return the stored result without applying the operation again.
```

## What if event publishing fails after DB write?

```text
In production I would use the outbox pattern: write the domain change and outbox record in the same database transaction, then publish asynchronously with retries.
```

## How would this scale?

```text
I would partition by accountId or customerId, keep the synchronous path small, and move reporting or analytics to asynchronous projections. I would design consumers to be idempotent because retries and at-least-once delivery are normal.
```

---

# 17. Common Mistakes to Avoid

Avoid:

- Coding silently
- Overengineering before a working version
- Ignoring null/empty input
- Using double for money
- Mutating state before all validation
- Forgetting duplicate/idempotency behavior
- Returning internal mutable lists directly
- Not testing edge cases
- Treating Cosmo output as automatically correct
- Getting defensive when interviewers suggest changes

---

# 18. Strong Staff-Level Signals

Use these naturally:

```text
I’ll start with the simplest design that satisfies the requirements, then evolve it if needed.
```

```text
The main invariant here is that we should not mutate state unless all validation passes.
```

```text
For retries, idempotency should be explicit.
```

```text
This is fine for an in-memory exercise, but in production I would need durability, concurrency control, and observability.
```

```text
I’ll use Cosmo to generate edge cases, but I’ll decide which ones apply to our requirements.
```

---

# 19. 7-Day Preparation Plan

## Day 1: Java Collections Refresh

Practice:

- HashMap
- HashSet
- ArrayDeque
- PriorityQueue
- TreeMap
- Comparator
- records
- enums

Problems:

- Two Sum
- Group Anagrams
- Top K Frequent Elements

## Day 2: Wallet / Ledger Problem

Build:

- create wallet
- deposit
- withdraw
- transfer
- transaction history
- idempotency

## Day 3: Rate Limiter + Sliding Window

Build:

- per-user sliding window limiter
- remaining quota
- retryAfterMillis
- per-tier limit

## Day 4: Payment Authorization

Build:

- account validation
- duplicate transaction detection
- insufficient funds
- daily limit
- decline reasons

## Day 5: Reconciliation

Build:

- exact match
- missing internal/external
- amount mismatch
- duplicate reference handling

## Day 6: Mock Interview

Do one 90-minute simulation:

```text
45 minutes implementation
20 minutes follow-up
15 minutes tests
10 minutes complexity/trade-offs
```

## Day 7: Polish

Review:

- Opening script
- Cosmo prompts
- Edge cases
- Complexity
- Staff-level production comments

---

# 20. Final Checklist

Before interview:

```text
Java syntax refreshed
CodeSignal environment tested
Chrome/Firefox/Edge ready
Zoom tested
Quiet room
Water ready
Resume nearby
Opening script ready
Cosmo prompts ready
```

During interview:

```text
Clarify
Think aloud
Start simple
Code incrementally
Test manually
Use Cosmo professionally
Accept feedback
Summarize trade-offs
```

---

# 21. Best Final Positioning

```text
I am a Staff/Principal backend engineer with strong fintech and distributed systems experience. In a coding interview, I focus on correctness, simple design, readable Java, edge cases, idempotency, and testability. I use AI as a reviewer and accelerator, but I own the design and final implementation.
```

That matches the Nubank pair-programming expectations very well.
